package com.example.buoi9_b1

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnChooseDateTime: ImageView
    private lateinit var tvSelectedDateTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnChooseDateTime = findViewById(R.id.btnChooseDateTime)
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime)

        // Khi người dùng nhấn nút "Chọn ngày giờ"
        btnChooseDateTime.setOnClickListener {
            showDatePickerDialog()
        }
    }

    // Hàm hiển thị DatePickerDialog
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = android.app.DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Cập nhật ngày đã chọn
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                showTimePickerDialog(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    // Hàm hiển thị TimePickerDialog
    private fun showTimePickerDialog(selectedDate: Calendar) {
        val hour = selectedDate.get(Calendar.HOUR_OF_DAY)
        val minute = selectedDate.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Cập nhật giờ đã chọn
                selectedDate.set(Calendar.HOUR_OF_DAY, selectedHour)
                selectedDate.set(Calendar.MINUTE, selectedMinute)

                // Hiển thị ngày giờ đã chọn
                displaySelectedDateTime(selectedDate)
            },
            hour, minute, true
        )

        timePickerDialog.show()
    }

    // Hàm hiển thị kết quả ngày giờ đã chọn trong TextView
    private fun displaySelectedDateTime(selectedDate: Calendar) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(selectedDate.time)

        // Hiển thị kết quả đẹp hơn trong TextView
        tvSelectedDateTime.text = "Ngày giờ đã chọn:\n$formattedDate"

        // Hiển thị một thông báo ngắn (Toast) khi hoàn tất
        Toast.makeText(this, "Lịch đã được thiết lập!", Toast.LENGTH_SHORT).show()
    }
}
