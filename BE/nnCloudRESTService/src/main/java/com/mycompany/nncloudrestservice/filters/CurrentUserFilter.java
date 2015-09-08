/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.filters;

import com.mycompany.nncloudrestservice.utils.CurrentUserContainer;
import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

/**
 *
 * @author Tomasz
 */
@PreMatching
@Priority(1)
public class CurrentUserFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getCookies().get("session_id").getValue();
        CurrentUserContainer.loadInstance(token);
    }

}
