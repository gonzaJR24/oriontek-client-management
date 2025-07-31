package com.client.management.oriontek_client_management.command.dto;

public record CreateAddressCommand(
        String street,
        String city,
        String province,
        String number,
        String country,
        Long clientId
) {
}