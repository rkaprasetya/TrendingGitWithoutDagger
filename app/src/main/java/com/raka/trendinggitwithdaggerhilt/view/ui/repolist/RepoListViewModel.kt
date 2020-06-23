package com.raka.trendinggitwithdaggerhilt.view.ui.repolist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.raka.myapplication.data.model.compact.ItemsCompact
import com.raka.trendinggitwithdaggerhilt.domain.RepoListUsecase
import com.raka.trendinggitwithdaggerhilt.repository.RepoRepositoryImpl
import com.raka.trendinggitwithdaggerhilt.view.utils.Resource
import com.raka.trendinggitwithdaggerhilt.view.utils.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoListViewModel (application: Application) : AndroidViewModel(application){
    private val repoListUseCase = RepoListUsecase(RepoRepositoryImpl.getInstance(application) )
    private val _repoListCompact = MutableLiveData<Resource<List<ItemsCompact>>>()
    val repoListCompact : LiveData<Resource<List<ItemsCompact>>>
            get() = _repoListCompact

    init {
        loadRepoList()
    }
    private fun loadRepoList(){
        viewModelScope.launch {
            _repoListCompact.value = Resource.loading(null)
            val isInternetAvailable = withContext(Dispatchers.IO){
                Util.isInternetAvailable()
            }

            if(isInternetAvailable){
                loadRemoteData()
            }else{
                loadLocalData()
            }
        }
    }
    private fun loadRemoteData(){
        viewModelScope.launch {
            repoListUseCase.getRepoListRemote()
                .onStart {  _repoListCompact.value = Resource.loading(null)
                    Log.e("onStart","Loading State")
                }
                .catch { error-> Log.e("Error","Server error message= ${error.message}")
                    _repoListCompact.value = Resource.error("${error.message}",null)
                    loadLocalData()
                }
                .collect { data->
                    if (data.isNullOrEmpty()){
                        _repoListCompact.value = Resource.error("Fail loading data",null)
                    }else{
                        _repoListCompact.value = Resource.success(data)
                    }
                }
        }
    }

    private fun loadLocalData(){
       viewModelScope.launch {
           repoListUseCase.repoListLocal().let {
               if(it.isNullOrEmpty()){
                   _repoListCompact.value = Resource.error("Fail loading data",null)
               }else{
                   _repoListCompact.value = Resource.success(it)
               }
           }
       }
    }
}