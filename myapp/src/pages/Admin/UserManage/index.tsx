import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {useRef} from 'react';
import {searchUsers} from "@/services/ant-design-pro/api";
import {Image} from "antd";

export const waitTimePromise = async (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

export const waitTime = async (time: number = 100) => {
  await waitTimePromise(time);
};

const columns: ProColumns<API.CurrentUser>[] = [
  {
    dataIndex: 'id',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: 'username',
    dataIndex: 'username',
    copyable: true,
  },
  {
    title: 'user account',
    dataIndex: 'userAccount',
    copyable: true,
  },
  {
    title: 'avatar',
    dataIndex: 'avatarUrl',
      render: (_, record) => (
        <div>
          <Image src={record.avatarUrl} width={50}/>
        </div>
      ),
   },
  {
    title: 'gender',
    dataIndex: 'gender',
  },
  {
    title: 'phone',
    dataIndex: 'phone',
    copyable: true,
  },
  {
    title: 'email',
    dataIndex: 'email',
    copyable: true,
  },
  {
    title: 'status',
    dataIndex: 'userStatus',
      valueType: 'select',
      valueEnum: {
        0: {
          text: 'normal',
          status: 'Default'
        },
        1: {
          text: 'deleted',
          status: 'Error',
        },

      },
  },
  {
    title: 'role',
    dataIndex: 'userRole',
      valueType: 'select',
      valueEnum: {
        0: {
          text: 'normal',
          status: 'Default'
        },
        1: {
          text: 'admin',
          status: 'Success',
        },
      },
  },
  {
    title: 'create time',
    dataIndex: 'createTime',
    valueType: 'date',
  },
];

export default () => {
  const actionRef = useRef<ActionType>();
  return (
    <ProTable<API.CurrentUser>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={async (params, sort, filter) => {
        console.log(sort, filter);
        await waitTime(2000);
        const userList = await searchUsers();
        return {
          data: userList
        }

      }}
      editable={{
        type: 'multiple',
      }}
      columnsState={{
        persistenceKey: 'pro-table-singe-demos',
        persistenceType: 'localStorage',
        defaultValue: {
          option: { fixed: 'right', disable: true },
        },
        onChange(value) {
          console.log('value: ', value);
        },
      }}
      rowKey="id"
      search={{
        labelWidth: 'auto',
      }}
      options={{
        setting: {
          listsHeight: 400,
        },
      }}
      form={{
        // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
        syncToUrl: (values, type) => {
          if (type === 'get') {
            return {
              ...values,
              created_at: [values.startTime, values.endTime],
            };
          }
          return values;
        },
      }}
      pagination={{
        pageSize: 5,
        onChange: (page) => console.log(page),
      }}
      dateFormatter="string"
      headerTitle="user manage"
    />
  );
};
