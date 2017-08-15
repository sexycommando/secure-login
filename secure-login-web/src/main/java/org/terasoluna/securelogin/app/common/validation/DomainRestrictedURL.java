/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.terasoluna.securelogin.app.common.validation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.hibernate.validator.constraints.URL;

@Documented
@Constraint(validatedBy = {DomainRestrictedURLValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
@URL
public @interface DomainRestrictedURL {

    String message() default "{org.terasoluna.securelogin.app.common.validation.DomainRestrictedURL.message}";

    Class<?>[] groups() default {};

    String[] allowedDomains() default {};

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface List {

        DomainRestrictedURL[] value();
    }

    Class<? extends Payload>[] payload() default {};

}
