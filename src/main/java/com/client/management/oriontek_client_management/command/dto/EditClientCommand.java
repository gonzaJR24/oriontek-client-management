package com.client.management.oriontek_client_management.command.dto;

import java.util.List;

import com.client.management.oriontek_client_management.domain.enums.ClientType;

public record EditClientCommand(ClientType type ,String name, List<CreateAddressCommand> addresses) {}