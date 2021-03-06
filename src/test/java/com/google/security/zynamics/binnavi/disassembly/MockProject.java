/*
Copyright 2014 Google Inc. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.google.security.zynamics.binnavi.disassembly;

import com.google.common.base.Preconditions;
import com.google.security.zynamics.binnavi.Database.Interfaces.SQLProvider;
import com.google.security.zynamics.binnavi.Database.MockClasses.MockSqlProvider;
import com.google.security.zynamics.binnavi.debug.debugger.DebuggerTemplate;
import com.google.security.zynamics.binnavi.debug.models.trace.TraceList;
import com.google.security.zynamics.binnavi.disassembly.AddressSpaces.CAddressSpace;
import com.google.security.zynamics.binnavi.disassembly.views.INaviView;
import com.google.security.zynamics.zylib.general.ListenerProvider;
import com.google.security.zynamics.zylib.types.lists.FilledList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class MockProject implements INaviProject {
  private final ListenerProvider<IProjectListener> m_listeners =
      new ListenerProvider<IProjectListener>();

  final SQLProvider provider;
  private final CProjectConfiguration m_configuration;
  private final CProjectContent m_content;
  private boolean m_isloaded = true;

  public MockProject(final SQLProvider provider) {
    this.provider =
        Preconditions.checkNotNull(provider, "Error: provider argument can not be null");
    m_configuration = new CProjectConfiguration(this,
        m_listeners,
        provider,
        0,
        "Mock Project",
        null,
        new Date(),
        new Date(),
        new ArrayList<DebuggerTemplate>());
    m_content = new CProjectContent(this,
        m_listeners,
        provider,
        new ArrayList<CAddressSpace>(),
        new ArrayList<INaviView>(),
        new FilledList<TraceList>());
  }

  public MockProject() {
    provider = new MockSqlProvider();
    m_configuration = new CProjectConfiguration(this,
        m_listeners,
        provider,
        0,
        "Mock Project",
        null,
        new Date(),
        new Date(),
        new ArrayList<DebuggerTemplate>());
    m_content = new CProjectContent(this,
        m_listeners,
        provider,
        new ArrayList<CAddressSpace>(),
        new ArrayList<INaviView>(),
        new FilledList<TraceList>());
  }

  @Override
  public void addListener(final IProjectListener listener) {
    m_listeners.addListener(listener);
  }

  @Override
  public boolean close() {
    if (m_isloaded) {
      m_isloaded = false;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int getAddressSpaceCount() {
    throw new IllegalStateException("Not yet implemented");
  }

  @Override
  public CProjectConfiguration getConfiguration() {
    return m_configuration;
  }

  @Override
  public CProjectContent getContent() {
    return m_content;
  }

  @Override
  public List<INaviView> getViewsWithAddresses(final List<UnrelocatedAddress> addresses,
      final boolean all) {
    throw new IllegalStateException("Not yet implemented");
  }

  @Override
  public boolean inSameDatabase(final IDatabaseObject provider) {
    throw new IllegalStateException("Not yet implemented");
  }

  @Override
  public boolean inSameDatabase(final SQLProvider provider) {
    throw new IllegalStateException("Not yet implemented");
  }

  @Override
  public boolean isLoaded() {
    return m_isloaded;
  }

  @Override
  public boolean isLoading() {
    return false;
  }

  @Override
  public void load() {
    m_isloaded = true;
  }

  @Override
  public String readSetting(final String key) {
    throw new IllegalStateException("Not yet implemented");
  }

  @Override
  public void removeListener(final IProjectListener listener) {
    m_listeners.removeListener(listener);
  }

  @Override
  public void writeSetting(final String key, final String value) {
    throw new IllegalStateException("Not yet implemented");
  }
}
