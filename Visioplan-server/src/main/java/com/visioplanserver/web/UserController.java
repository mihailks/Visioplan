package com.visioplanserver.web;

import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.model.view.UserWithRoleViewModel;
import com.visioplanserver.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private static int currentPageOn;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "user-profile-view";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "user-profile-edit";
    }

    @PostMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable("id") Long id,
                              @Valid UserProfileEditDTO userProfileEditDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userProfileEditDTO", userProfileEditDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileEditDTO", bindingResult);
            return "redirect:/user/profile/edit";
        }
        userProfileEditDTO.setId(id);
        userService.updateUserProfile(userProfileEditDTO);

        return "redirect:/user/profile";
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Principal principal) {
        UserWithRoleViewModel user = userService.findUserRoleByUsername(principal.getName());
        userService.deleteUser(id);
        if (user.getRole().contains(RolesEnum.ADMIN)) {
            return "redirect:/user/all";
        }
        //TODO: logout after delete user. How to make POST request from controller?
        return "redirect:/users/logout";
    }

    @GetMapping("/all")
    public String getAllPages(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/all/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<UserWithRoleViewModel> page = userService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        Long totalUsers = page.getTotalElements();
        List<UserWithRoleViewModel> users = page.getContent();
        currentPageOn = currentPage;
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("users", users);

        return "admin-users-all";
    }

    @PostMapping("/promote/{id}")
    public String promoteUser(@PathVariable("id") Long id) {
        userService.promoteUser(id);
        return "redirect:/user/all/" + currentPageOn;
    }

    @PostMapping("/demote/{id}")
    public String demoteUser(@PathVariable("id") Long id) {
        userService.demoteUser(id);
        return "redirect:/user/all/" + currentPageOn;
    }


}

