package tp.appliSpring.web.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {
	public BasicController() {
		System.out.println("BasicController load ...");
	}

//http://localhost:8080/.../mvc/basic/helloworld
	@RequestMapping("/helloWorld")
	public String helloWorld(Model model) {
		model.addAttribute("message", "Hello World!");
		System.out.println("helloWorld returning showMessage ...");
		return "showMessage"; // .jsp in /WEB-INF/views/
	}
}