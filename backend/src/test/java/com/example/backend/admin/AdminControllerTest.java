package com.example.backend.admin;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.when;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.admin.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    @Test
    void testChangeRole() throws Exception {
        when(adminService.changePersonRole(Mockito.<Long>any(), anyBoolean())).thenReturn(null);

        ChangeRoleDTO changeRoleDTO = new ChangeRoleDTO();
        changeRoleDTO.setStatus(true);
        changeRoleDTO.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(changeRoleDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/changeRole")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetPromotionRequests() throws Exception {
        when(adminService.getPromotionRequests()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/promotionRequests");

        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
