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
package org.terasoluna.securelogin.app.welcome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Inject
    AccountSharedService accountSharedService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(@AuthenticationPrincipal LoggedInUser userDetails, Model model) {

        Account account = userDetails.getAccount();

        model.addAttribute("account", account);

        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ FIRSTNAME = {}", account.getFirstName());

        if (accountSharedService.isCurrentPasswordExpired(account.getUsername())) {
            ResultMessages messages = ResultMessages.warning().add("w.sl.pe.0001");
            model.addAttribute(messages);
        }

        LocalDateTime lastLoginDate = userDetails.getLastLoginDate();
        if (lastLoginDate != null) {
            model.addAttribute("lastLoginDate",
                lastLoginDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return "welcome/home";

    }

}
