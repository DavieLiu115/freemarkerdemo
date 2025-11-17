SET
@parentId = 1990330380183281664;
-- 用户信息管理菜单
INSERT INTO `sys_menu`
    (`id`, `title`, `parent_id`, `type`, `path`, `name`, `component`, `redirect`, `icon`, `is_external`, `is_cache`, `is_hidden`, `permission`, `sort`, `status`, `create_user`, `create_time`)
VALUES (@parentId, '用户信息管理', 1000, 2, '/system/user', 'User', 'system/user/index', NULL, NULL, b'0', b'0', b'0', NULL, 1, 1, 1, NOW());

-- 用户信息管理按钮
INSERT INTO `sys_menu`
    (`id`, `title`, `parent_id`, `type`, `permission`, `sort`, `status`, `create_user`, `create_time`)
VALUES (1990330380200058880, '列表', @parentId, 3, 'system:user:list', 1, 1, 1, NOW()),
       (1990330380200058881, '详情', @parentId, 3, 'system:user:get', 2, 1, 1, NOW()),
       (1990330380200058882, '新增', @parentId, 3, 'system:user:create', 3, 1, 1, NOW()),
       (1990330380200058883, '修改', @parentId, 3, 'system:user:update', 4, 1, 1, NOW()),
       (1990330380200058884, '删除', @parentId, 3, 'system:user:delete', 5, 1, 1, NOW()),
       (1990330380200058885, '导出', @parentId, 3, 'system:user:export', 6, 1, 1, NOW());

