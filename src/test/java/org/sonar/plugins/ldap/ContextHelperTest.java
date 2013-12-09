/*
 * Sonar CAS Plugin
 * Copyright (C) 2013 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.ldap;

import org.junit.Test;

import javax.naming.Context;
import javax.naming.NamingException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ContextHelperTest {

  @Test
  public void shouldSwallow() throws Exception {
    Context context = mock(Context.class);
    doThrow(new NamingException()).when(context).close();
    ContextHelper.close(context, true);
    ContextHelper.closeQuetly(context);
  }

  @Test(expected = NamingException.class)
  public void shouldNotSwallow() throws Exception {
    Context context = mock(Context.class);
    doThrow(new NamingException()).when(context).close();
    ContextHelper.close(context, false);
  }

  @Test
  public void normal() throws NamingException {
    ContextHelper.close(null, true);
    ContextHelper.closeQuetly(null);
    ContextHelper.close(mock(Context.class), true);
  }

}
