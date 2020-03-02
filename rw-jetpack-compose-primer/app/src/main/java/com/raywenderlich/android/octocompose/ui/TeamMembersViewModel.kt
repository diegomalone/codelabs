/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.octocompose.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.android.octocompose.model.Member
import com.raywenderlich.android.octocompose.repository.Repository
import com.raywenderlich.android.octocompose.repository.remote.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamMembersViewModel : ViewModel() {

  private val repository: Repository = RemoteRepository()
  private lateinit var teamName: String

  private val members: MutableLiveData<List<Member>> by lazy {
    MutableLiveData<List<Member>>().also {
      loadMembers()
    }
  }

  fun getMembers(team: String): LiveData<List<Member>> {
    teamName = team
    return members
  }

  private fun loadMembers() {
    repository.retrieveTeamMembers(teamName, object : Callback<List<Member>> {
      override fun onResponse(call: Call<List<Member>>?, response: Response<List<Member>>?) {
        val membersResponse = response?.body()
        if (membersResponse != null) {
          members.postValue(membersResponse)
        } else {
          clearMembersAndShowError(null)
        }
      }

      override fun onFailure(call: Call<List<Member>>?, t: Throwable?) {
        clearMembersAndShowError(t)
      }
    })
  }


  private fun clearMembersAndShowError(t: Throwable?) {
    members.postValue(listOf())
    t?.let {
      Log.i("ViewModel", t.localizedMessage ?: "Unknown error")
    }
  }
}