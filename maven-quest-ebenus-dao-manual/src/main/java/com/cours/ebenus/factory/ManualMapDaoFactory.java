/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.factory;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListRoleDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListUtilisateurDao;
import com.cours.ebenus.dao.manual.map.impl.ManualMapRoleDao;
import com.cours.ebenus.dao.manual.map.impl.ManualMapUtilisateurDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapDaoFactory extends AbstractDaoFactory {

    private static final Log log = LogFactory.getLog(ManualArrayDaoFactory.class);

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return new ManualMapUtilisateurDao();
    }

    @Override
    public IRoleDao getRoleDao() {
        return new ManualMapRoleDao();
    }
}
