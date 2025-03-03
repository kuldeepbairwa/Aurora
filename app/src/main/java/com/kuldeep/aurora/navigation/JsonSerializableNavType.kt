@file:Suppress("UNCHECKED_CAST")

package com.kuldeep.aurora.navigation
import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KType


fun typeMapOf(ktypes: List<KType>): Map<KType, @JvmSuppressWildcards NavType<*>> {
    return ktypes.associate {
        if (it.isMarkedNullable) {
            it to JsonSerializableNullableNavType(serializer(it))
        } else {
            it to JsonSerializableNavType(serializer(it).cast())
        }
    }
}

private fun <T> KSerializer<*>.cast(): KSerializer<T> = this as KSerializer<T>

private data class JsonSerializableNavType<T : Any>(
    private val serializer: KSerializer<T>,
) : NavType<T>(isNullableAllowed = false) {
    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.encodedAsString())
    }

    override fun get(bundle: Bundle, key: String): T {
        return parseValue(bundle.getString(key)!!)
    }

    override fun serializeAsValue(value: T): String {
        return Uri.encode(value.encodedAsString())
    }

    override fun parseValue(value: String): T {
        return value.decodedFromString()
    }

    private fun T.encodedAsString(): String = Json.encodeToString(serializer, this)

    private fun String.decodedFromString(): T = Json.decodeFromString(serializer, this)
}


private data class JsonSerializableNullableNavType<T : Any?>(
    private val serializer: KSerializer<T?>,
) : NavType<T?>(isNullableAllowed = true) {
    override fun put(bundle: Bundle, key: String, value: T?) {
        bundle.putString(key, value?.encodedAsString())
    }

    override fun get(bundle: Bundle, key: String): T? {
        val data = bundle.getString(key) ?: return null
        return parseValue(data)
    }

    override fun serializeAsValue(value: T?): String {
        if (value == null) return "null"
        return Uri.encode(value.encodedAsString())
    }

    override fun parseValue(value: String): T? {
        if (value == "null") return null
        return value.decodedFromString()
    }

    private fun T.encodedAsString(): String = Json.encodeToString(serializer, this)

    private fun String.decodedFromString(): T? = Json.decodeFromString(serializer, this)
}