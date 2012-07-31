dataSource {
    pooled = true
    url = "jdbc:mysql://localhost/image?useUnicode=true&characterEncoding=utf8"
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = ""
    dialect = org.hibernate.dialect.MySQL5UTF8InnoDBDialect
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/image?useUnicode=true&characterEncoding=utf8"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/image?useUnicode=true&characterEncoding=utf8"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/image?useUnicode=true&characterEncoding=utf8"
        }
    }
}
