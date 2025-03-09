package com.kuldeep.aurora.features.newChat.data

import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import androidx.core.text.isDigitsOnly
import com.kuldeep.aurora.features.newChat.domain.model.Contact
import com.kuldeep.aurora.features.newChat.domain.repository.ContactsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ContactsRepository {
    override suspend fun fetchContacts(): List<Contact> {

        val contacts = mutableListOf<Contact>()
        val contentResolver = context.contentResolver

//        defining what colums we need to retrieve
        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
        )

        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME
        )

        cursor?.use {

            val idColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)


            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumnIndex)
                val name = cursor.getString(nameColumnIndex)

                // Query phone numbers for this contact
                val phoneNumber = getPhoneNumber(contentResolver, id)

                // Add the contact to the list
                contacts.add(Contact(id, name, phoneNumber))
            }



        }



        return contacts
            .distinct()
            .filterNot {
                it.phoneNumber.contains(Regex("\"^[0-9+]+\\\$\""))
                        && it.phoneNumber.isEmpty()
            }
            .map { it.copy(phoneNumber = it.phoneNumber.replace("-","")) }
            .sortedBy {
            it.name
        }


    }

    private fun getPhoneNumber(contentResolver: ContentResolver, contactId: Long): String {
        var phoneNumber: String? = null

        // Query phone numbers for the contact
        val phoneCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(contactId.toString()),
            null
        )

        phoneCursor?.use { cursor ->
            if (cursor.moveToFirst()) {
                val phoneNumberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                phoneNumber = cursor.getString(phoneNumberColumnIndex)
            }
        }

        return phoneNumber?:""
    }
}