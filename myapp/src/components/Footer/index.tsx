import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      links={[
        {
          key: 'kaki',
          title: 'Kaki',
          href: 'https://pro.ant.design',
          blankTarget: true, //true:打开新页面 false:当前页面
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/kaki-bai',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
