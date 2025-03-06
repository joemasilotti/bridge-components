import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector

fun image(imageName: String?): ImageVector? {
    return when (imageName) {
        "Add" -> Icons.Filled.Add
        "Edit" -> Icons.Filled.Edit
        else -> null
    }
}