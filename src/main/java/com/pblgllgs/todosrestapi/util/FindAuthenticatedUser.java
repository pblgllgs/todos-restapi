package com.pblgllgs.todosrestapi.util;
/*
 *
 * @author pblgl
 * Created on 15-05-2025
 *
 */

import com.pblgllgs.todosrestapi.entity.User;

public interface FindAuthenticatedUser {
    User getAuthenticatedUser();
}
