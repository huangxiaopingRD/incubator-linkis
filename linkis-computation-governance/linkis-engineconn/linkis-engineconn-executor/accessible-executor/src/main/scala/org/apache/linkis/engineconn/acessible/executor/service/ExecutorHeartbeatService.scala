/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.linkis.engineconn.acessible.executor.service

import org.apache.linkis.engineconn.executor.entity.Executor
import org.apache.linkis.manager.common.protocol.node.{NodeHeartbeatMsg, NodeHeartbeatRequest}

trait ExecutorHeartbeatService {

  /**
   * 定时上报心跳信息，依据Executor不同进行实现
   *
   * @param executor
   */
  def reportHeartBeatMsg(executor: Executor): Unit

  def generateHeartBeatMsg(executor: Executor): NodeHeartbeatMsg

  def dealNodeHeartbeatRequest(nodeHeartbeatRequest: NodeHeartbeatRequest): NodeHeartbeatMsg

  def setSelfUnhealthy(reason: String): Unit

}

object ExecutorHeartbeatServiceHolder {

  private var executorHeartbeatService: ExecutorHeartbeatService = _

  def registerHeartBeatService(executorHeartbeatService: ExecutorHeartbeatService): Unit =
    this.executorHeartbeatService = executorHeartbeatService

  def getDefaultHeartbeatService(): ExecutorHeartbeatService = executorHeartbeatService

}
