using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;

namespace ToolBox
{
    public class Connection
    {
        private DbProviderFactory _Factory;
        private string _ConnectionString;

        public Connection(string invariantName, string connectionString)
        {
            _Factory = DbProviderFactories.GetFactory(invariantName);
            _ConnectionString = connectionString;
        }

        public DataTable GetDataTable(Command command)
        {
            using (DbConnection connection = CreateConnection())
            {
                using (DbCommand sqlCommand = CreateCommand(command, connection))
                {
                    using (DbDataAdapter sqlDataAdapter = _Factory.CreateDataAdapter())
                    {
                        sqlDataAdapter.SelectCommand = sqlCommand;
                        DataTable dataTable = new DataTable();

                        sqlDataAdapter.Fill(dataTable);
                        return dataTable;
                    }
                }
            }
        }

        public object ExecuteScalar(Command command)
        {
            using (DbConnection connection = CreateConnection())
            {
                using (DbCommand sqlCommand = CreateCommand(command, connection))
                {
                    connection.Open();
                    object o = sqlCommand.ExecuteScalar();
                    return (o is DBNull) ? null : o;
                }
            }
        }

        public int ExecuteNonQuery(Command command)
        {
            using (DbConnection connection = CreateConnection())
            {
                using (DbCommand sqlCommand = CreateCommand(command, connection))
                {                    
                    connection.Open();
                    return sqlCommand.ExecuteNonQuery();
                }
            }
        }

        private DbConnection CreateConnection()
        {
            DbConnection connection = _Factory.CreateConnection();
            connection.ConnectionString = _ConnectionString;

            return connection;
        }

        private DbCommand CreateCommand(Command command, DbConnection connection)
        {
            DbCommand sqlCommand = connection.CreateCommand();
            sqlCommand.CommandText = command.Query;
            sqlCommand.CommandType = (command.IsStoredProcedure) ? CommandType.StoredProcedure : CommandType.Text;

            foreach (KeyValuePair<string, object> kvp in command.Parameters)
            {
                DbParameter parameter = _Factory.CreateParameter();
                parameter.ParameterName = kvp.Key;
                parameter.Value = kvp.Value;

                sqlCommand.Parameters.Add(parameter);
            }

            return sqlCommand;
        }
    }
}
