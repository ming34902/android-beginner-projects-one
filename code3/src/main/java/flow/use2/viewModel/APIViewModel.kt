package flow.use2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import flow.use.entity.LoginRegisterResponse
import flow.use.entity.LoginRegisterResponseWrapper
import flow.use2.repository.APIRepository
import kotlinx.coroutines.launch

class APIViewModel : ViewModel() {

    // LiveData 绑定UI
    val userLiveData = MutableLiveData<LoginRegisterResponseWrapper<LoginRegisterResponse>>()

    fun requestLogin(username: String, userpwd: String) {
        // 全局的 GlobalScope 作用于协程，默认是异步线程
        // viewModelScope 作用于协程，默认是 android的主线程 == Dispatchers.Main
        viewModelScope.launch {
            // 左边代码 UI主线程，右边代码 挂起执行的异步线程，执行完成后回复UI线程更新UI
            userLiveData.value = APIRepository().requestLogin(username, userpwd)
        }
    }
}
