/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cary.activity.timecat.manager.easeui.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.easeui.adapter.NewFriendsMsgAdapter;
import com.cary.activity.timecat.manager.easeui.db.InviteMessgeDao;
import com.cary.activity.timecat.manager.easeui.domain.InviteMessage;
import com.hyphenate.easeui.ui.EaseBaseActivity;

import java.util.Collections;
import java.util.List;

/**
 * Application and notification
 *
 */
@SuppressLint("Registered")
public class NewFriendsMsgActivity extends EaseBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.em_activity_new_friends_msg);

		ListView listView = (ListView) findViewById(R.id.list);
		InviteMessgeDao dao = new InviteMessgeDao(this);
		List<InviteMessage> msgs = dao.getMessagesList();
		Collections.reverse(msgs);

		NewFriendsMsgAdapter adapter = new NewFriendsMsgAdapter(this, 1, msgs);
		listView.setAdapter(adapter);
		dao.saveUnreadMessageCount(0);
		
	}

	public void back(View view) {
		finish();
	}
}