package haui.doan.stores.controllers.api;

import haui.doan.stores.business.ProvideService;
import haui.doan.stores.dto.request.ProvideRequest;
import haui.doan.stores.dto.response.ErrorResponse;
import haui.doan.stores.framework.CommonConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProvideController {

    private final ProvideService provideService;


    @PostMapping("/admin/provide")
    public Map<String, Object> saveProvide(@Valid ProvideRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorResponse> errors = new ArrayList<>();
        if (result.hasErrors()) {
            result.getFieldErrors().stream().forEach(fieldError -> {
                errors.add(new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
            });
        }
        if (provideService.existProvide(request.getName(), request.getNameOld())) {
            errors.add(new ErrorResponse("name", "Tên nhà cung cấp đã tồn tại"));
        }
        if (errors.isEmpty()) {
            map.put("status", 200);
            provideService.saveProvide(request);
        } else {
            map.put("status", 101);
            map.put("errors", errors);
        }
        return map;
    }

    @GetMapping("/admin/provide/{id}")
    public ProvideRequest edit(@PathVariable("id") Long id) {
        return provideService.findProvideByIdAndStatus(id, CommonConstant.STATUS.NO_DELETE);
    }

    @DeleteMapping("/admin/provide/{id}")
    public Map<String, Object> deleteProvide(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        ProvideRequest provide = provideService.findProvideByIdAndStatus(id, CommonConstant.STATUS.NO_DELETE);
        if (provide == null) {
            map.put("status", 101);
        } else {
            map.put("status", 200);
            provideService.deleteProvide(id);
        }
        return map;
    }
}
