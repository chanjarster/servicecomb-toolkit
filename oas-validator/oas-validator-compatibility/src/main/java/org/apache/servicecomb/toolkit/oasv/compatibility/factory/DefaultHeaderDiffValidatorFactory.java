/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.toolkit.oasv.compatibility.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.servicecomb.toolkit.oasv.compatibility.validators.header.HeaderAddInRequestBodyNotAllowedDiffValidator;
import org.apache.servicecomb.toolkit.oasv.compatibility.validators.header.HeaderDelInResponseNotAllowedDiffValidator;
import org.apache.servicecomb.toolkit.oasv.diffvalidation.api.HeaderDiffValidator;
import org.apache.servicecomb.toolkit.oasv.diffvalidation.factory.HeaderDiffValidatorFactory;
import org.apache.servicecomb.toolkit.oasv.diffvalidation.factory.SchemaDiffValidatorFactory;
import org.apache.servicecomb.toolkit.oasv.diffvalidation.skeleton.header.HeaderSchemaDiffValidator;
import org.springframework.stereotype.Component;

@Component
public class DefaultHeaderDiffValidatorFactory implements HeaderDiffValidatorFactory {

  private final SchemaDiffValidatorFactory schemaDiffValidatorFactory;

  public DefaultHeaderDiffValidatorFactory(
      SchemaDiffValidatorFactory schemaDiffValidatorFactory) {
    this.schemaDiffValidatorFactory = schemaDiffValidatorFactory;
  }

  @Override
  public List<HeaderDiffValidator> create() {

    List<HeaderDiffValidator> validators = new ArrayList<>();

    // skeletons
    validators.add(new HeaderSchemaDiffValidator(schemaDiffValidatorFactory.create()));

    // concretes
    validators.add(new HeaderAddInRequestBodyNotAllowedDiffValidator());
    validators.add(new HeaderDelInResponseNotAllowedDiffValidator());

    return Collections.unmodifiableList(validators);
  }
}
