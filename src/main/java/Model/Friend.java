/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author estudiante.fit
 */
public class Friend {
    private int ci_user;
    private int ci_friend;
    
    public Friend(int user_id, int friend_id) {
        this.ci_user = user_id;
        this.ci_friend = friend_id;
    }
    
    public Friend() {}

    public int getCi_user() {
        return ci_user;
    }

    public void setCi_user(int user_id) {
        this.ci_user = user_id;
    }

    public int getCi_friend() {
        return ci_friend;
    }

    public void setCi_friend(int friend_id) {
        this.ci_friend = friend_id;
    }
    
}
