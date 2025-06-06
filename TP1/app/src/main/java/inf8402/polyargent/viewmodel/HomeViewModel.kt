package inf8402.polyargent.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import inf8402.polyargent.model.transaction.Account
import inf8402.polyargent.model.transaction.CategoryReport
import inf8402.polyargent.model.transaction.TransactionDatabase


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val accountDao = TransactionDatabase.getDatabase(application, viewModelScope).accountDao()
    private val transactionDao = TransactionDatabase.getDatabase(application, viewModelScope).transactionDao()

    fun getTotalAmount(): LiveData<String> {
        return accountDao.getAccountById(1)
    }

    fun setTotalAmount(totalAmount: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountDao.update(Account(1, totalAmount))
        }
    }

    fun getExpensesTotalAmount(startDate: String, endDate: String): LiveData<Int> {
        return transactionDao.getExpensesTotalAmountByDates(startDate, endDate)
    }

    fun getIncomesTotalAmount(startDate: String, endDate: String): LiveData<Int> {
        return transactionDao.getIncomesTotalAmountByDates(startDate, endDate)
    }

    fun getIncomeTransactionsByDateRange(startDate: String, endDate: String): LiveData<List<CategoryReport>> {
        return transactionDao.getTransactionsByDateRange(startDate, endDate, "INCOME")
    }

    fun getExpenseTransactionsByDateRange(startDate: String, endDate: String): LiveData<List<CategoryReport>> {
        return transactionDao.getTransactionsByDateRange(startDate, endDate, "EXPENSE")
    }
}