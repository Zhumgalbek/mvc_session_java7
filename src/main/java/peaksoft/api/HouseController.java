package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Comment;
import peaksoft.model.House;
import peaksoft.service.CommentService;
import peaksoft.service.HouseService;

@Controller
@RequestMapping
public class HouseController {
    private final HouseService houseService;
    private final CommentService commentService;

    @Autowired
    public HouseController(HouseService houseService,
                           CommentService commentService) {
        this.houseService = houseService;
        this.commentService = commentService;
    }

    @GetMapping("/allHouses")
    private String getAllHouse(Model model) {
        model.addAttribute("allHouses", houseService.getAllHouse());
        return "/mainPage";
    }

    @GetMapping("/getHouse/{houseId}")
    private String getHouseById(@PathVariable("houseId") Long id, Model model) {
        model.addAttribute("house", houseService.getHouseById(id));
        model.addAttribute("comments", commentService.getCommentsByHouseId(id));
        System.out.println(commentService.getCommentsByHouseId(id));
        return "/innerPage";
    }

    @GetMapping("/new")
    private String newHouse(Model model) {
        model.addAttribute("newHouse", new House());
        return "/newHouse";
    }

    @PostMapping("/save")
    private String saveHouse(@ModelAttribute("newHouse") House house) {
        houseService.saveHouse(house);
        return "redirect:/allHouses";
    }

    @PostMapping("/delete/{id}")
    private String deleteHouse(@PathVariable Long id) {
        houseService.deleteHouseById(id);
        return "redirect:/allHouses";
    }

    @GetMapping("/{id}/newComment")
    private String newComment(Model model) {
        model.addAttribute("comment", new Comment());
        return "/saveComment";
    }

    @PostMapping("{id}/saveComment")
    private String saveComment(@ModelAttribute("comment") Comment comment,
                               @PathVariable Long id) {
        commentService.saveCommentToHouse(id, comment);
        return "redirect:/getHouse/{id}";
    }
}
