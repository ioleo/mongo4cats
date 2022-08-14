/*
 * Copyright 2020 Kirill5k
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mongo4cats.bson

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MyDocumentSpec extends AnyWordSpec with Matchers {

  "A MyDocument" when {
    val jsonString = """{"name": {"first": "John", "last": "Smith", "aliases": ["foo", "bar"]}, "tags": [42, "test"]}"""

    val nameDoc = MyDocument("first" -> "John", "last" -> "Smith", "aliases" -> List("foo", "bar"))
    val tags    = List(42, "test")

    val testDocument = MyDocument("name" -> nameDoc, "tags" -> tags)

    "dealing with json" should {
      "create itself from json string" in {
        val result = MyDocument.parse(jsonString)

        result mustBe testDocument
        result.getList("tags") mustBe Some(tags)
        result.getDocument("name") mustBe Some(nameDoc)
        result.getObjectId("_id") mustBe None
      }

      "convert itself to json" in {
        val result = testDocument.toJson

        result mustBe jsonString
      }
    }

    "calling toString" should {
      "produce string representation" in {
        val result = testDocument.toString

        result mustBe "Document(name -> Document(first -> John, last -> Smith, aliases -> List(foo, bar)), tags -> List(42, test))"
      }
    }

    "getting a value by key" should {
      "handle null and undefined" in {
        val doc = MyDocument.parse("""{"propA":null,"propB":undefined}""")

        doc.getString("propA") mustBe None
        doc.getString("propB") mustBe None
      }
    }
  }
}
