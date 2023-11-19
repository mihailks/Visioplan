package com.visioplanserver.model.view;

import java.util.Set;

public record CompanyViewModel(
        String id,
        String name,
        String address,
        String city,
        String country,
        String phone,
        String email,
        String website,
        Set<UserViewModel> employees) {
}
