package wvsdev.costumerconnectapi.controller.dto;

public record CreateUserDto(String fullName,
                            String cpf,
                            String email,
                            String phoneNumber) {
}
