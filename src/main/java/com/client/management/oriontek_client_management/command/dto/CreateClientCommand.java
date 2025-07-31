package com.client.management.oriontek_client_management.command.dto;

import java.util.List;

import com.client.management.oriontek_client_management.domain.enums.ClientType;

public record CreateClientCommand(String name, ClientType type, List<CreateAddressCommand> addresses) {}