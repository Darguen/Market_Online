package entities

import android.os.Parcel
import android.os.Parcelable

data class Shopping_Cart (
    val budget: Int,
    val amountShop: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(budget)
        parcel.writeInt(amountShop)
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
