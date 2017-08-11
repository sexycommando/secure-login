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
package org.terasoluna.securelogin.app.passwordchange;

import javax.inject.Inject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.service.passwordchange.PasswordChangeService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

@Controller
@RequestMapping("password")
public class PasswordChangeController {

    @Inject
    PasswordChangeService passwordService;

    @RequestMapping(params = "form")
    public String showForm(PasswordChangeForm form,
        @AuthenticationPrincipal LoggedInUser userDetails, Model model) {

        Account account = userDetails.getAccount();
        model.addAttribute(account);
        return "passwordchange/changeForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String change(@AuthenticationPrincipal LoggedInUser userDetails,
        @Validated PasswordChangeForm form, BindingResult bindingResult,
        Model model) {

        Account account = userDetails.getAccount();
        if (bindingResult.hasErrors()
            || !account.getUsername().equals(form.getUsername())) {
            model.addAttribute(account);
            return "passwordchange/changeForm";
        }

        passwordService.updatePassword(form.getUsername(),
            form.getNewPassword());

        return "redirect:/password?complete";

    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String changeComplete() {
        return "passwordchange/changeComplete";
    }

    @ModelAttribute("passwordChangeForm")
    public PasswordChangeForm setUpPasswordChangeForm() {
        return new PasswordChangeForm();
    }
}
