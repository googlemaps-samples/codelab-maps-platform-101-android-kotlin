// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codelabs.buildyourfirstmap.place

import android.content.Context
import com.google.codelabs.buildyourfirstmap.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Reads a list of place JSON objects from the file places.json.
 */
class PlacesReader(private val context: Context) {

    // GSON object responsible for converting from JSON to a Place object
    private val gson = Gson()

    // InputStream representing places.json
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    /**
     * Reads the list of place JSON objects in the file places.json and returns a list of Place
     * objects
     */
    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
}