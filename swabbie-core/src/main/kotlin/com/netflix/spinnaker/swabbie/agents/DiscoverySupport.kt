/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.swabbie.agents

import com.netflix.appinfo.InstanceInfo
import com.netflix.discovery.DiscoveryClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DiscoverySupport(
  @Autowired(required = false) val discoveryClient: DiscoveryClient?
) {
  val log: Logger = LoggerFactory.getLogger(javaClass)
  inline fun ifUP(action: () -> Unit) {
    log.info("Discovery status {}", discoveryClient?.instanceRemoteStatus ?: "not available")
    if (discoveryClient == null || discoveryClient.instanceRemoteStatus == InstanceInfo.InstanceStatus.UP) {
      action.invoke()
    }
  }
}
