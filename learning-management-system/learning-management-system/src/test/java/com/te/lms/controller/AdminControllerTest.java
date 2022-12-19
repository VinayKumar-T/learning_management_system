package com.te.lms.controller;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.te.lms.entity.dto.ApproveDto;
import com.te.lms.entity.dto.MessageDto;
import com.te.lms.entity.dto.NewBatchDto;
import com.te.lms.entity.dto.NewMentorDto;
import com.te.lms.entity.dto.RejectDto;
import com.te.lms.entity.dto.RequestListDto;
import com.te.lms.entity.dto.SkillsDto;
import com.te.lms.entity.dto.TechnologiesDto;
import com.te.lms.entity.dto.UpdateBatchDto;
import com.te.lms.entity.dto.UpdateMentorDto;
import com.te.lms.entity.enums.BatchStatus;
import com.te.lms.exception.EmployeeNotFoundExcpetion;
import com.te.lms.response.ApiResponse;
import com.te.lms.service.AdminService;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdminService adminService;

	@InjectMocks
	private AdminController adminController;

	@Autowired
	private ObjectMapper objectMapper;

	// test case for registering mentor successfully.......
	@Test
	public void testRegisterMentor() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		List<SkillsDto> skillsDto = Lists.newArrayList();
		SkillsDto skillDto = new SkillsDto();
		skillDto.setSkillName("Java");
		skillsDto.add(skillDto);
		NewMentorDto mentorDto = NewMentorDto.builder().mentorEmailId("a@a.com").mentorName("Rakesh")
				.mentorEmployeeId("TY002").skillsDto(skillsDto).build();
		MessageDto messageDto = MessageDto.builder().emailId("a@a.com").message("message").build();

		Mockito.when(adminService.registerMentor(Mockito.any()))
				.thenReturn(Optional.ofNullable(messageDto.getMessage()));

		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.post("/admin/registermentor").accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(mentorDto)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ApiResponse<String> readValue = objectMapper.readValue(contentAsString, ApiResponse.class);
		assertEquals("mentor has been registered successfully", readValue.getMessage());
	}

	// test case for register mentor which returns bad Http response as bad Request
	@Test
	public void testRegisterMentor_Returns400()
			throws JsonProcessingException, UnsupportedEncodingException, Exception {
		NewMentorDto mentorDto = NewMentorDto.builder().mentorEmailId("s@s.com").mentorName("Srikar")
				.mentorEmployeeId("TE001").skillsDto(Lists.newArrayList()).build();
		MessageDto messageDto = MessageDto.builder().emailId("s@s.com").message("message").build();

		Mockito.when(adminService.registerMentor(Mockito.any())).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/registermentor").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(mentorDto)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	// TestCase to delete mentor
	@Test
	public void testDeleteMentor() throws Exception {

		NewMentorDto mentorDto = NewMentorDto.builder().mentorEmailId("a@a.com").mentorEmployeeId("TY001")
				.mentorName("Rakesh reddy").skillsDto(Lists.newArrayList()).build();
		List<SkillsDto> skills = Lists.newArrayList();
		SkillsDto skillDto = new SkillsDto();
		skillDto.setSkillName("skill-1");
		skills.add(skillDto);
		mentorDto.setSkillsDto(skills);

		Mockito.when(adminService.deleteMentor(mentorDto.getMentorEmployeeId()))
				.thenReturn(Optional.ofNullable(mentorDto.getMentorEmployeeId()));

		mockMvc.perform(MockMvcRequestBuilders.put("/admin/deletementor/{mentorId}", "TY001")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(mentorDto)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	// test case to delete mentor that return Error 400
	@Test
	public void testDeleteMentor_Returns400() throws Exception {
		NewMentorDto mentorDto = new NewMentorDto();
		Mockito.when(adminService.deleteMentor(mentorDto.getMentorEmployeeId())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.put("/admin/deletementor/{mentorId}", "TE001")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

//		// test case to update mentor successfully.....
	@Test
	public void testUpdateMentor() throws Exception {
		UpdateMentorDto updateMentorDto = UpdateMentorDto.builder().emailId("s@s.com").mentorName("Srikar")
				.skillsDto(Lists.newArrayList()).build();
		List<SkillsDto> skills = Lists.newArrayList();
		SkillsDto skillDto = new SkillsDto();
		skillDto.setSkillName("skill-1");
		skills.add(skillDto);
		updateMentorDto.setSkillsDto(skills);

		Mockito.when(adminService.updateMentor(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(true));

		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.put("/admin/update/{empId}", "TE001")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(updateMentorDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ApiResponse<String> readValue = objectMapper.readValue(contentAsString, ApiResponse.class);
		assertEquals("mentor details are updated successfully", readValue.getMessage());
	}

	// Testcase testUpdateMentor_Returns400
	@Test
	public void testUpdateMentor_Returns400() throws Exception {

		UpdateMentorDto updateMentorDto = new UpdateMentorDto();

		Mockito.when(adminService.updateMentor(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(false));

		mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/{empId}", "TE001")
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(updateMentorDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	// search mentor
	@Test
	public void testSearchMentor() throws UnsupportedEncodingException, Exception {
		NewMentorDto mentorDto = NewMentorDto.builder().mentorEmailId("a@a.com").mentorEmployeeId("TY001")
				.mentorName("vinay").skillsDto(Lists.newArrayList()).build();
		List<SkillsDto> skills = Lists.newArrayList();
		SkillsDto skillDto = new SkillsDto();
		skillDto.setSkillName("skill-1");
		skills.add(skillDto);
		mentorDto.setSkillsDto(skills);

		Mockito.when(adminService.read(Mockito.any())).thenReturn(Optional.ofNullable(mentorDto));

		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.get("/admin/searchmentor/{mentorId}", "TY001")
						.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ApiResponse<String> readValue = objectMapper.readValue(contentAsString, ApiResponse.class);
		assertEquals("Mentor Details", readValue.getMessage());
	}

	@Test
	public void testSearchMentor_Returns4001() throws Exception {
		NewMentorDto mentorDto = new NewMentorDto();

		Mockito.when(adminService.read(Mockito.any())).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/searchmentor/{mentorId}", "TY001")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	// Test case for register batch

	@Test
	public void testRegisterBatch() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		NewBatchDto newBatchDto = NewBatchDto.builder().bathchId("BATCH-01").batchName("SEP-21")
				.batchStatus(BatchStatus.INPROGRESS).technologiesDto(Lists.newArrayList()).build();
		TechnologiesDto technologiesDto = new TechnologiesDto();
		technologiesDto.setTechnology("JAVA");
		newBatchDto.getTechnologiesDto().add(technologiesDto);

		Mockito.when(adminService.registerBatch(Mockito.any()))
				.thenReturn(Optional.ofNullable(newBatchDto.getBathchId()));

		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.post("/admin/registerbatch").accept(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(newBatchDto))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ApiResponse<String> readValue = objectMapper.readValue(contentAsString, ApiResponse.class);
		assertEquals("BATCH-01", readValue.getData());
	}
	// test testRegisterBatch_Returns400

	@Test
	public void testRegisterBatch_Returns400() throws Exception {

		NewBatchDto newBatchDto = new NewBatchDto();
		Mockito.when(adminService.registerBatch(Mockito.any())).thenReturn(Optional.ofNullable(null));
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/registerbatch").accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(newBatchDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	// test case of delete batch
	@Test
	public void testDeleteBatch() throws Exception {
		String batchId = "Batch-01";
		Mockito.when(adminService.deleteBatch(batchId)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.put("/admin/deletebatch/{batchId}", "Batch-01")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	// test case for delete batch that return response as BAD REQUEST
	@Test
	public void testDeleteBatch_Returns400() throws Exception {
		String batchId = "Batch-01";

		Mockito.when(adminService.deleteBatch(batchId)).thenReturn(false);

		mockMvc.perform(MockMvcRequestBuilders.put("/admin/deletebatch/{batchId}", "Batch-01")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	// test upodatebatch

	@Test
	public void testUpdateBatch() throws UnsupportedEncodingException, Exception {

		UpdateBatchDto updateBatchDto = UpdateBatchDto.builder().batchName("ABC").batchStatus(BatchStatus.INPROGRESS)
				.mentorName("srikar").technologiesDto(Lists.newArrayList()).build();

		Mockito.when(adminService.updateBatch(Mockito.any(), Mockito.any())).thenReturn(true);

		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.put("/admin/updatebatch/{batchId}", "TE001")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(updateBatchDto))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ApiResponse<String> readValue = objectMapper.readValue(contentAsString, ApiResponse.class);
		assertEquals("TE001", readValue.getData());

	}

	// testUpdateBatch_Returns400
	@Test
	public void testUpdateBatch_Returns400() throws UnsupportedEncodingException, Exception {

		UpdateBatchDto updateBatchDto = UpdateBatchDto.builder().batchName("ABC").batchStatus(BatchStatus.INPROGRESS)
				.mentorName("Srikar").technologiesDto(Lists.newArrayList()).build();

		Mockito.when(adminService.updateBatch(Mockito.any(), Mockito.any())).thenReturn(false);

		mockMvc.perform(MockMvcRequestBuilders.put("/admin/updatebatch/{batchId}", "TE001")
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(updateBatchDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	// Test Approve Request
	@Test
	public void testApproveRequest() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		ApproveDto approveDto = ApproveDto.builder().batchId("TE001").batchName("SEP-21").build();

		MessageDto messageDto = MessageDto.builder().emailId("s@s.com").message("This message").build();

		Mockito.when(adminService.approve(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(messageDto));

		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.post("/admin/approve/employee/{employeeId}", "TE001")
						.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(approveDto))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ApiResponse<String> readValue = objectMapper.readValue(contentAsString, ApiResponse.class);
		assertEquals("employee has been approved", readValue.getMessage());

	}

	// Test testApproveRequest_Returns400()
	@Test
	public void testApproveRequest_Returns400() throws JsonProcessingException, Exception {
		ApproveDto approveDto = ApproveDto.builder().batchId("TY001").batchName("SEP-21").build();

		Mockito.when(adminService.approve(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/approve/employee/{employeeId}", "TY001")
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(approveDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	// Test RejectRequest
	@Test
	public void testRejectRequest() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		RejectDto rejectDto = RejectDto.builder().message("Need to imporve").build();
		MessageDto messageDto = MessageDto.builder().emailId("s@s.com").message("Need to imporve").build();

		Mockito.when(adminService.reject(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(messageDto));

		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.put("/admin/reject/employee/{employeeId}", "TE001")
						.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(rejectDto))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ApiResponse<String> readValue = objectMapper.readValue(contentAsString, ApiResponse.class);
		assertEquals("employee has been rejected", readValue.getMessage());

	}

	// Test testRejectRequest_Returns400()
	@Test
	public void testRejectRequest_Returns400() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		RejectDto rejectDto = RejectDto.builder().message("Need to imporve").build();
		MessageDto messageDto = MessageDto.builder().emailId("a@a.com").message("Need to imporve").build();
		Mockito.when(adminService.reject(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(null));

		String contentAsSting = mockMvc
				.perform(MockMvcRequestBuilders.put("/admin/reject/employee/{employeeId}", "TE001")
						.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(rejectDto))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse()
				.getContentAsString();
		EmployeeNotFoundExcpetion exception = objectMapper.readValue(contentAsSting, EmployeeNotFoundExcpetion.class);
		assertEquals("unable to find the employee", exception.getMessage());

	}

//testGetRequestList
	@Test
	public void testGetRequestList() throws UnsupportedEncodingException, Exception {

		List<RequestListDto> requestList = Lists.newArrayList();
		RequestListDto requestsListsDto = new RequestListDto();
		RequestListDto requestListsDto2 = new RequestListDto();
		requestList.add(requestListsDto2);
		requestList.add(requestsListsDto);

		Mockito.when(adminService.getRequestList()).thenReturn(Optional.ofNullable(requestList));
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/requestlist").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}

}
