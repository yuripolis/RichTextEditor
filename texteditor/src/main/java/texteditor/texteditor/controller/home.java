package texteditor.texteditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import texteditor.texteditor.model.Post;
import texteditor.texteditor.repository.campeonatoRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class home {
    @Autowired
    private campeonatoRepository repo;

    @GetMapping("/")
    public ModelAndView main(){
        ModelAndView mv = new ModelAndView("/index");
        List<Post> posts = this.repo.findAll();
        mv.addObject("posts",posts);
        return mv;
    }


    @GetMapping("/editor")
    public ModelAndView newpost(){

        ModelAndView mv = new ModelAndView("/editor");
        return mv;
    }

    @PostMapping("/newpost")
    public ModelAndView create(@Valid Post blogPost, Errors error, Model model) {
        if(error.hasErrors()){
            System.out.println("Post Contem erro");
            ModelAndView mv = new ModelAndView("/editor");

        }
        this.repo.save(blogPost);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/article/{id}")
    public ModelAndView showarticle(@PathVariable Long id){
        Optional<Post> post = this.repo.findById(id);
        if(post.isPresent()){
            System.out.println("******************************ID FOUND************************");
            System.out.println(post.get().getName());

            ModelAndView mv = new ModelAndView("/article");
            mv.addObject("showpost",post);
            return mv;
        }else{
            return new ModelAndView("redirect:/");
        }


    }
}
