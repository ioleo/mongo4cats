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

package mongo4cats.client

import com.mongodb.{
  MongoDriverInformation => JMongoDriverInformation,
  MongoClientSettings => JMongoClientSettings,
  ServerAddress => JServerAddress
}

import java.net.InetSocketAddress

object settings {

  type ServerAddress = JServerAddress
  object ServerAddress {
    def apply(host: String, port: Int): ServerAddress    = new JServerAddress(host, port)
    def apply(address: InetSocketAddress): ServerAddress = apply(address.getHostName, address.getPort)
  }

  type MongoClientSettings = JMongoClientSettings
  object MongoClientSettings {
    def builder: JMongoClientSettings.Builder                                = JMongoClientSettings.builder()
    def builder(settings: MongoClientSettings): JMongoClientSettings.Builder = JMongoClientSettings.builder(settings)
  }

  type MongoDriverInformation = JMongoDriverInformation
  object MongoDriverInformation {
    def builder: JMongoDriverInformation.Builder                                      = JMongoDriverInformation.builder()
    def builder(information: MongoDriverInformation): JMongoDriverInformation.Builder = JMongoDriverInformation.builder(information)
  }
}
