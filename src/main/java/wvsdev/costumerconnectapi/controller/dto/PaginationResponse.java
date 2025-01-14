package wvsdev.costumerconnectapi.controller.dto;

public record PaginationResponse(Integer page,
                                 Integer pageNumber,
                                 Long totalElements,
                                 Integer totalPages) {
}
