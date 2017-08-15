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
package org.terasoluna.securelogin.app.account;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.terasoluna.gfw.common.validator.constraints.Compare;
import org.terasoluna.gfw.common.validator.constraints.Compare.Operator;
import org.terasoluna.securelogin.app.common.validation.DomainRestrictedEmail;
import org.terasoluna.securelogin.app.common.validation.DomainRestrictedURL;
import org.terasoluna.securelogin.app.common.validation.FileExtension;
import org.terasoluna.securelogin.app.common.validation.FileNamePattern;
import org.terasoluna.securelogin.app.common.validation.NotContainControlChars;
import org.terasoluna.securelogin.app.common.validation.NotContainControlCharsExceptNewlines;
import org.terasoluna.securelogin.app.common.validation.UploadFileMaxSize;
import org.terasoluna.securelogin.app.common.validation.UploadFileNotEmpty;
import org.terasoluna.securelogin.app.common.validation.UploadFileRequired;

@Data
@Compare(left = "email", right = "confirmEmail", operator = Operator.EQUAL, requireBoth = true, node = Compare.Node.ROOT_BEAN)
public class AccountCreateForm implements Serializable {

    public static interface Confirm {
    };

    public static interface CreateAccount {
    };

    private static final long serialVersionUID = 1L;

    @NotNull
    @NotContainControlChars
    @Size(min = 4, max = 128)
    private String username;

    @NotNull
    @NotContainControlChars
    @Size(min = 1, max = 128)
    private String firstName;

    @NotNull
    @NotContainControlChars
    @Size(min = 1, max = 128)
    private String lastName;

    @NotNull
    @NotContainControlChars
    @Size(min = 1, max = 128)
    @DomainRestrictedEmail(allowedDomains = {"domainexample.co.jp",
        "somedomainexample.co.jp"}, allowSubDomain = true)
    private String email;

    @NotNull
    @NotContainControlChars
    private String confirmEmail;

    @NotNull
    @NotContainControlChars
    @DomainRestrictedURL(allowedDomains = {"jp"})
    private String url;

    @UploadFileRequired(groups = Confirm.class)
    @UploadFileNotEmpty(groups = Confirm.class)
    @UploadFileMaxSize
    @FileExtension(extensions = {"jpg", "png", "gif"})
    @FileNamePattern(pattern = "[a-zA-Z0-9_-]+\\.[a-zA-Z]{3}")
    private MultipartFile image;

    @NotNull(groups = CreateAccount.class)
    @Size(max = 40)
    private String imageId;

    @NotNull
    @NotContainControlCharsExceptNewlines
    private String profile;

}
