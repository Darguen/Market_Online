package entities

import android.os.Parcel
import android.os.Parcelable

class StoreData(
    val name: String?,
    val description: String?,
    val id: Int,
    val location: String?,
    val products: List<String>,
    val stock: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readInt()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(location)
        parcel.writeStringList(products)
        parcel.writeInt(stock)
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