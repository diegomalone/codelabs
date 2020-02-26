/*
 * Copyright (c) 2019 Razeware LLC
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.spacedaily.network

import com.squareup.moshi.Json

/**
 * {
"date": "2019-10-19",
"explanation": "The failed unit was beyond the reach of the robotic Canadarm2.  Therefore, this repair of the International Space Station would require humans. The humans on duty were NASA's Jessica Meir and Christina Koch. This was the fourth spacewalk for Koch, the first for Meir, and the first all-female spacewalk in human history.  The first woman to walk in space was Svetlana Savitskaya in 1984.  Koch (red stripe) and Meir are pictured hard at work on the P6 Truss, with solar panels and the darkness of space in the background.  Working over seven hours, the newly installed Battery Charge / Discharge Unit (BCDU) was successfully replaced and, when powered up, operated normally.",
"hdurl": "https://apod.nasa.gov/apod/image/1910/KochMeirISS_NASA_734.jpg",
"media_type": "image",
"service_version": "v1",
"title": "All Female Spacewalk Repairs Space Station",
"url": "https://apod.nasa.gov/apod/image/1910/KochMeirISS_NASA_960.jpg"
}
 */
data class PhotoResponse(val date: String, val explanation: String, val hdurl: String?,
                         @Json(name = "media_type") val mediaType: String,
                         @Json(name = "service_version") val serviceVersion: String,
                         val title: String,
                         val url: String
)

