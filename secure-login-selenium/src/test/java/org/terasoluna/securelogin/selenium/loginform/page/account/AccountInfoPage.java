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
package org.terasoluna.securelogin.selenium.loginform.page.account;

import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AuthenticationRequiredPage;
import org.terasoluna.securelogin.selenium.loginform.page.passwordchange.PasswordChangePage;

import static org.openqa.selenium.By.id;

public class AccountInfoPage extends AuthenticationRequiredPage {

    {
        url = "/accounts";
    }

    public AccountInfoPage(WebDriverOperations webDriverOperations,
            String applicationContextURL) {
        super(webDriverOperations, applicationContextURL);
    }

    public PasswordChangePage goToPasswordChangePage() {
        webDriverOperations.click(id("changePassword"));
        waitDefaultInterval();
        return new PasswordChangePage(webDriverOperations, applicationContextUrl);
    }

}
