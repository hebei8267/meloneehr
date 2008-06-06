package cn.hb.view.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Move_001")
@Scope("request")
public class Move_001 {
    public String nextPage() {
        System.out.println("##############");
        return "sec001";
    }
}
