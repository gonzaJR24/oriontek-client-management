package com.client.management.oriontek_client_management.queries.dto;

import java.util.List;

import com.client.management.oriontek_client_management.domain.enums.ClientType;

public record ClientView(Long id, String name, ClientType type, List<AddressView> addresses ) {

}
