/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.ozone.protocol;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.apache.hadoop.hdds.annotation.InterfaceAudience;
import org.apache.hadoop.hdds.protocol.proto.HddsProtos.ExtendedDatanodeDetailsProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.ContainerReportsProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.LayoutVersionProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.NodeReportProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.PipelineReportsProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.SCMHeartbeatRequestProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.SCMHeartbeatResponseProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.SCMRegisteredResponseProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.SCMVersionRequestProto;
import org.apache.hadoop.hdds.protocol.proto.StorageContainerDatanodeProtocolProtos.SCMVersionResponseProto;
import org.apache.hadoop.hdds.scm.ScmConfig;
import org.apache.hadoop.security.KerberosInfo;

/**
 * The protocol spoken between datanodes and SCM. For specifics please the
 * Protoc file that defines this protocol.
 */
@KerberosInfo(
    serverPrincipal = ScmConfig.ConfigStrings.HDDS_SCM_KERBEROS_PRINCIPAL_KEY)
@InterfaceAudience.Private
public interface StorageContainerDatanodeProtocol {

  @SuppressWarnings("checkstyle:ConstantName")
  /**
   * Version 1: Initial version.
   */
  long versionID = 1L;

  /**
   * Returns SCM version.
   * @return Version info.
   */
  SCMVersionResponseProto getVersion(SCMVersionRequestProto versionRequest)
      throws IOException;

  /**
   * Used by data node to send a Heartbeat.
   * @param heartbeat Heartbeat
   * @return - SCMHeartbeatResponseProto
   * @throws IOException
   */
  SCMHeartbeatResponseProto sendHeartbeat(SCMHeartbeatRequestProto heartbeat)
      throws IOException, TimeoutException;

  /**
   * Register Datanode.
   * @param extendedDatanodeDetailsProto - extended Datanode Details.
   * @param nodeReport - Node Report.
   * @param containerReportsRequestProto - Container Reports.
   * @param layoutInfo - Layout Version Information.
   * @return SCM Command.
   */
  SCMRegisteredResponseProto register(
      ExtendedDatanodeDetailsProto extendedDatanodeDetailsProto,
      NodeReportProto nodeReport,
      ContainerReportsProto containerReportsRequestProto,
      PipelineReportsProto pipelineReports,
      LayoutVersionProto layoutInfo) throws IOException;

}
