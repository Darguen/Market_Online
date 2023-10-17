package entities

import android.os.Parcel
import android.os.Parcelable

data class UserData (
    val name: String?,
    val mail: String?,
    val phone: Int,
    val rut: Int,
    val id: Int,
    val password: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(mail)
        parcel.writeInt(phone)
        parcel.writeInt(rut)
        parcel.writeInt(id)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}