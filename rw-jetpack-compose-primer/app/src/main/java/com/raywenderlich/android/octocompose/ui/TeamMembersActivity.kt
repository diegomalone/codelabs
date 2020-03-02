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

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import com.raywenderlich.android.octocompose.R
import com.raywenderlich.android.octocompose.model.Member
import com.raywenderlich.android.octocompose.model.PreviewMember

class TeamMembersActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel = ViewModelProviders.of(this)[TeamMembersViewModel::class.java]
    viewModel.getMembers("raywenderlich").observe(this, Observer<List<Member>> { members ->
      Log.i("TeamMembersActivity", members.toString())
    })

    setContent {
      MaterialTheme {
        TeamMember(PreviewMember.member)
      }
    }
  }
}

@Composable
fun TeamMember(member: Member) {
  val image = +image(member.avatarUrl) ?: +imageResource(R.drawable.placeholder)

  FlexRow(
    crossAxisAlignment = CrossAxisAlignment.Center,
    modifier = Spacing(8.dp)
  ) {
    inflexible {
      Clip(shape = RoundedCornerShape(8.dp)) {
        Container(width = 80.dp, height = 80.dp, modifier = Spacing(right = 8.dp)) {
          DrawImage(image)
        }
      }
    }
    expanded(1f) {
      Text(text = member.login)
    }
    inflexible {
      Text(text = member.type)
    }
  }
}