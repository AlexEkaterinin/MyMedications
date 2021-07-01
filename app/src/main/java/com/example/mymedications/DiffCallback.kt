import androidx.recyclerview.widget.DiffUtil
import com.example.mymedications.api.MedicationModel

class DiffCallback(
    private val newData: List<MedicationModel>,
    private val oldData: List<MedicationModel>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldData.size

    override fun getNewListSize() = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition] == newData[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition] == newData[newItemPosition]
}