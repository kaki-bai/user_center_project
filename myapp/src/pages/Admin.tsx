import { PageContainer } from '@ant-design/pro-components';
import '@umijs/max';
import React, { ReactNode } from 'react';

interface AdminProps {
  children?: ReactNode;  // 使用 ReactNode 类型为 children 提供类型定义
}

const Admin: React.FC<AdminProps> = ({ children }) => {

  alert(children);
  return (
    <PageContainer content="MANAGE">

      {children}

      <p
        style={{
          textAlign: 'center',
          marginTop: 24,
        }}
      >
        Want to add more pages? Please refer to{' '}
        <a href="https://pro.ant.design/docs/block-cn" target="_blank" rel="noopener noreferrer">
          use block
        </a>
        。
      </p>
    </PageContainer>
  );
};
export default Admin;
