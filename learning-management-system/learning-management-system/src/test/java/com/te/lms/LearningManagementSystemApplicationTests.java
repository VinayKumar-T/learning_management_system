/*
 * package com.te.lms;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * 
 * import java.util.ArrayList; import java.util.List; import java.util.Optional;
 * import java.util.stream.Stream;
 * 
 * import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import
 * org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.mock.mockito.MockBean;
 * 
 * import com.te.lms.controller.AdminController; import
 * com.te.lms.entity.Mentor; import com.te.lms.entity.dto.NewMentorDto; import
 * com.te.lms.repository.MentorRepositorty; import
 * com.te.lms.service.AdminService;
 * 
 * @SpringBootTest class LearningManagementSystemApplicationTests {
 * 
 * // @MockBean // private AdminService adminService;
 * 
 * @MockBean
 * 
 * private MentorRepositorty mentorRepositorty;
 * 
 * @InjectMocks private AdminService adminService;
 * 
 * @Test void test_getMentors() { List<Mentor> nmd = new ArrayList<Mentor>();
 * nmd.add(new Mentor("A", "", "", "", null, null)); nmd.add(new Mentor("B", "",
 * "", "", null, null));
 * 
 * 
 * Mockito.when(mentorRepositorty.findAll()).thenReturn(nmd);
 * System.out.println(adminService.getMentors().get().size());
 * assertEquals(2,adminService.getMentors().get().size()); }
 * 
 * // @Test // public void searchMentor() { // //
 * Mockito.when(adminService.read("")).thenReturn(Optional.ofNullable(new
 * NewMentorDto().builder().mentorName(null) //
 * .mentorEmailId(null).mentorEmployeeId(null).skillsDto(null).build())); //
 * assertEquals(new NewMentorDto(), adminService.read(null)); // }
 * 
 * }
 */