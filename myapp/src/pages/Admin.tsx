import { PageContainer } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';
const Admin: React.FC = () => {
  // const children = props;
  return (
    <PageContainer content={' 这个页面只有 admin 权限才能查看'}>
      {/*{children}*/}

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
